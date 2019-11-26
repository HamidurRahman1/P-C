package com.hamidur.cunyfirst.daoTier.util;

import com.hamidur.cunyfirst.daoTier.models.Address;
import com.hamidur.cunyfirst.daoTier.models.Contact;
import com.hamidur.cunyfirst.daoTier.models.Course;
import com.hamidur.cunyfirst.daoTier.models.Gender;
import com.hamidur.cunyfirst.daoTier.models.HighSchoolInfo;
import com.hamidur.cunyfirst.daoTier.models.Instructor;
import com.hamidur.cunyfirst.daoTier.models.InstructorCourse;
import com.hamidur.cunyfirst.daoTier.models.Person;
import com.hamidur.cunyfirst.daoTier.models.Student;
import com.hamidur.cunyfirst.daoTier.models.Term;
import com.hamidur.cunyfirst.daoTier.models.TransferInfo;
import com.hamidur.cunyfirst.viewTier.models.InstructorLogin;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Utility
{
//    private ApplicationContext applicationContext;

    public static com.hamidur.cunyfirst.viewTier.models.Course toViewCourse(Course course)
    {
        return new com.hamidur.cunyfirst.viewTier.models.Course(course.getCourseTitle(), course.getCourseName(),
                course.getCourseLevel(), course.getCourseCredits(), course.getDescription());
    }

    public static Course toDaoCourse(com.hamidur.cunyfirst.viewTier.models.Course course)
    {
        return new Course(course.getCourseTitle(), course.getCourseName(),
                course.getCourseLevel(), course.getCourseCredits(), course.getDescription());
    }

    public static Integer toDaoCourseLevel(com.hamidur.cunyfirst.viewTier.models.CourseLevel courseLevel)
    {
        return courseLevel.getValue();
    }

    public static com.hamidur.cunyfirst.viewTier.models.CourseLevel toViewCourseLevel(Integer courseLevel)
    {
        return com.hamidur.cunyfirst.viewTier.models.CourseLevel.valueOf(String.valueOf(courseLevel));
    }

    public static Instructor toDaoInstructor(com.hamidur.cunyfirst.viewTier.models.Instructor instructor)
    {
        return new Instructor(instructor.getFirstName(), instructor.getLastName(), instructor.getSsn(),
                LocalDate.parse(instructor.getDateOfBirth()), instructor.getGender());
    }

    public static Set<InstructorCourse> toDaoInstructorCourses
            (Set<com.hamidur.cunyfirst.viewTier.models.InstructorCourse> instructorCourses)
    {
        Set<InstructorCourse> courses = new LinkedHashSet<>();

        instructorCourses.forEach(e ->
        {
            InstructorCourse instructorCourse = new InstructorCourse();
            instructorCourse.setCourse(Utility.toDaoCourse(e.getCourse()));
            instructorCourse.setInstructor(Utility.toDaoInstructor(e.getInstructor()));
            instructorCourse.setTerm(Utility.toDaoTerm(e.getTerm()));
            courses.add(instructorCourse);
        });
        return courses;
    }

    public static LocalDate toLocalDate(Date sqlDate)
    {
        return sqlDate.toLocalDate();
    }

    public static Date toSqlDate(LocalDate localDate)
    {
        return Date.valueOf(localDate);
    }

    public static String toViewGender(String gender)
    {
        return Gender.valueOf(gender.toUpperCase()).getValue();
    }

    public static Address toDaoAddress(com.hamidur.cunyfirst.viewTier.models.Address address)
    {
        return new Address(address.getStreet(), address.getCrossStreet(),
                address.getCity(), address.getState(), address.getZipCode());
    }

    public static com.hamidur.cunyfirst.viewTier.models.Address toViewAddress(Address address)
    {
        return new com.hamidur.cunyfirst.viewTier.models.Address(address.getStreet(), address.getCrossStreet(),
                address.getCity(), address.getState(), address.getZipCode());
    }

    public static com.hamidur.cunyfirst.viewTier.models.Student toViewStudent(Student student)
    {
        com.hamidur.cunyfirst.viewTier.models.Student viewStudent = new com.hamidur.cunyfirst.viewTier.models.Student();

        viewStudent.setFirstName(student.getPerson().getFirstName());
        viewStudent.setLastName(student.getPerson().getLastName());
        viewStudent.setSsn(student.getPerson().getSsn());
        viewStudent.setDateOfBirth(student.getPerson().getDateOfBirth().toString());
        viewStudent.setGender(toViewGender(student.getPerson().getGender()));

        viewStudent.setAddress(toViewAddress(student.getAddresses().iterator().next()));
        viewStudent.setHighSchoolInfo(toViewHighSchoolInfo(student.getHighSchoolInfo()));
        viewStudent.setContact(toViewContact(student.getContact()));
        viewStudent.setTransferInfo(toViewTransferInfo(student.getTransferInfo()));

        return viewStudent;
    }

    public static Student toDaoStudent(com.hamidur.cunyfirst.viewTier.models.Student student)
    {
        Student daoStudent = new Student();
        Person person = new Person();

        person.setFirstName(student.getFirstName());
        person.setLastName(student.getLastName());
        person.setSsn(student.getSsn());
        person.setDateOfBirth(LocalDate.parse(student.getDateOfBirth()));
        person.setGender(student.getGender());

        daoStudent.setPerson(person);

        daoStudent.addAddress(Utility.toDaoAddress(student.getAddress()));
        daoStudent.setContact(Utility.toDaoContact(student.getContact()));
        daoStudent.setHighSchoolInfo(Utility.toDaoHighSchoolInfo(student.getHighSchoolInfo()));
        daoStudent.setTransferInfo(Utility.toDaoTransferInfo(student.getTransferInfo()));

        return daoStudent;
    }

    public static com.hamidur.cunyfirst.viewTier.models.TransferInfo toViewTransferInfo(TransferInfo transferInfo)
    {
        return new com.hamidur.cunyfirst.viewTier.models.TransferInfo
                (transferInfo.getTransferSchoolName(), toViewTerm(transferInfo.getTerm()));
    }

    public static TransferInfo toDaoTransferInfo(com.hamidur.cunyfirst.viewTier.models.TransferInfo transferInfo)
    {
        if(transferInfo == null) return new TransferInfo();
        return new TransferInfo(transferInfo.getTransferSchoolName(), toDaoTerm(transferInfo.getTerm()));
    }

    public static com.hamidur.cunyfirst.viewTier.models.Term toViewTerm(Term term)
    {
        return new com.hamidur.cunyfirst.viewTier.models.Term(term.getTermName(), term.getTermYear());
    }

    public static Term toDaoTerm(com.hamidur.cunyfirst.viewTier.models.Term term)
    {
        return new Term(term.getTermName(), term.getTermYear());
    }

    public static com.hamidur.cunyfirst.viewTier.models.HighSchoolInfo toViewHighSchoolInfo(HighSchoolInfo schoolInfo)
    {
        com.hamidur.cunyfirst.viewTier.models.HighSchoolInfo view =
                new com.hamidur.cunyfirst.viewTier.models.HighSchoolInfo();

        view.setHighSchoolName(schoolInfo.getHighSchoolName());
        view.setCity(schoolInfo.getCity());
        view.setCountry(schoolInfo.getCountry());
        view.setYear(schoolInfo.getYear());

        return view;
    }

    public static HighSchoolInfo toDaoHighSchoolInfo(com.hamidur.cunyfirst.viewTier.models.HighSchoolInfo schoolInfo)
    {
        return new HighSchoolInfo(schoolInfo.getHighSchoolName(), schoolInfo.getYear(), schoolInfo.getCity(),
                schoolInfo.getCountry());
    }

    public static com.hamidur.cunyfirst.viewTier.models.Contact toViewContact(Contact contact)
    {
        return new com.hamidur.cunyfirst.viewTier.models.Contact(contact.getPhone().getPhone(), contact.getEmail(),
                contact.getCollegeEmail());
    }

    public static Contact toDaoContact(com.hamidur.cunyfirst.viewTier.models.Contact contact)
    {
        return new Contact(contact.getCollegeEmail(), contact.getEmail(), contact.getPhone());
    }

    public static com.hamidur.cunyfirst.viewTier.models.Instructor toViewInstructor(Instructor daoInstructor)
    {
        com.hamidur.cunyfirst.viewTier.models.Instructor instructor =
                new com.hamidur.cunyfirst.viewTier.models.Instructor();

        instructor.setInstructorId(daoInstructor.getInstructorId());
        instructor.setFirstName(daoInstructor.getPerson().getFirstName());
        instructor.setLastName(daoInstructor.getPerson().getLastName());
        instructor.setSsn(daoInstructor.getPerson().getSsn());
        instructor.setDateOfBirth(daoInstructor.getPerson().getDateOfBirth().toString());
        instructor.setGender(daoInstructor.getPerson().getGender());

        instructor.setLogin(Utility.toViewInstructorLogin(daoInstructor.getLogin()));
        instructor.setInstructorCourses(Utility.toViewInstructorCourses(daoInstructor.getInstructorCourses()));

        return instructor;
    }

    private static Set<com.hamidur.cunyfirst.viewTier.models.InstructorCourse> toViewInstructorCourses
            (Set<InstructorCourse> daoInstructorCourses)
    {
        Set<com.hamidur.cunyfirst.viewTier.models.InstructorCourse> courses = new LinkedHashSet<>();

        daoInstructorCourses.forEach(e ->
        {
            com.hamidur.cunyfirst.viewTier.models.InstructorCourse instructorCourse =
                    new com.hamidur.cunyfirst.viewTier.models.InstructorCourse();

            instructorCourse.setCourse(Utility.toViewCourse(e.getCourse()));
            instructorCourse.setInstructor(Utility.toViewInstructor(e.getInstructor()));
            instructorCourse.setTerm(Utility.toViewTerm(e.getTerm()));
            courses.add(instructorCourse);
        });
        return courses;
    }

    private static InstructorLogin toViewInstructorLogin(com.hamidur.cunyfirst.daoTier.models.InstructorLogin daoLogin)
    {
        return new InstructorLogin(daoLogin.getUserName(), daoLogin.getPassword());
    }
}
