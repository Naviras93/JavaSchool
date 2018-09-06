package ejb;

import domain.AttendanceDomain;
import domain.StudentDomain;
import jpa.Attendance;
import jpa.Course;
import jpa.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class AttendanceServiceImpl implements AttendanceService {

    @PersistenceContext
    EntityManager em;

    @Override
    public StringBuilder getAttendanceStatistics(Long courseId, Long studentId) {
        Student student = em.find(Student.class, studentId);
        Course course = em.find(Course.class, courseId);
        Query query = em.createNamedQuery("AttendanceFindByCourseAndUser");
        query.setParameter("student", student);
        query.setParameter("course", course);

        List<Attendance> list = query.getResultList();

        return calculateStatistic(list);
    }

    @Override
    public void saveAttendances(List<StudentDomain> students, Long currentCourseId) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String now = dtf.format(localDate);
            Date d = formatter.parse(now);

            Course c = em.find(Course.class, currentCourseId);
            for(StudentDomain student: students){

                //Sök i databasen om det finns en attendance med samma datum, student och kurs.
                //Om det gör det, uppdatera attendance, annars: lägg till attendance.

                Query search = em.createNamedQuery("findMatchingAttendance");
                search.setParameter("courseId", currentCourseId);
                search.setParameter("studentId", student.getId());
                search.setParameter("date", d);

                List<Attendance> a = search.getResultList();

                if(a.size() == 1 && a.get(0).isPresence() != student.getPresence())
                {
                    Long id = a.get(0).getId();
                    Attendance attendance = em.find(Attendance.class, id);
                    attendance.setPresence(student.getPresence());
                    em.merge(attendance);

                }

                else if (a.size() < 1){
                    Student s = em.find(Student.class, student.getId());
                    Attendance attendance = new Attendance(d, student.getPresence(), c, s);
                    em.persist(attendance);
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StringBuilder getCourseStatistics(Long courseid) {
        Query query = em.createNamedQuery("AttendanceFindByCourse");
        query.setParameter("courseid", courseid);

        List<Attendance> list = query.getResultList();
        return calculateStatistic(list);
    }

    @Override
    public StringBuilder getStudentStatistics(Long studentId) {
        Query query = em.createNamedQuery("AttendanceFindByStudent");
        query.setParameter("studentid", studentId);

        List<Attendance> list = query.getResultList();
        return calculateStatistic(list);
    }

    @Override
    public Set<Date> getAttendanceDatesByCourse(Long courseId) {

        Query query = em.createNamedQuery("AttendanceFindByCourse");
        query.setParameter("courseid", courseId);

        Set<Date> dateList = new HashSet<>();
        List<Attendance> list = query.getResultList();

        for(Attendance ad: list){
            dateList.add(ad.getDate());
        }

        /*
        Course course = em.find(Course.class, courseId);

        List<AttendanceDomain> adList = course.getAttendances().stream().
                map(a->new AttendanceDomain(a.getId(),a.getDate(),a.isPresence())).
                collect(Collectors.toList());

        Set<Date> dateList = new HashSet<>();
        for(AttendanceDomain ad: adList){
            dateList.add(ad.getDate());
        }
*/
        return dateList;

    }

    public StringBuilder calculateStatistic(List<Attendance> list){
        StringBuilder statistic = new StringBuilder("NO DATA");
        if (list.size()>0) {
            double presence = 0.0d;
            for (Attendance a: list) {
                if(a.isPresence())
                {
                    presence++;
                }
            }
            double totalAttendance = presence/list.size() * 100;
            statistic.setLength(0);
            statistic.append(totalAttendance).setLength(statistic.indexOf("."));
            statistic.append("%");

        }
        return statistic;
    }
}
