
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Student Center</title>
</head>
<body>
        <%@include file="StudentNameID.jsp"%>

        <div id="studentContainer">

            <div class="courses">
                <form action="/student/get/getAllCourses">
                    <input type="submit" value="Offered Courses">
                </form>
            </div>

            <div class="fafsas">
                <a href="/student/get/getFAFSAs">Get FAFSA's</a>
            </div>

            <div class="courses">
                <a href="/student/get/getCourseHistory">Course History</a>
            </div>

            <div class="personal">
                <div class="address">
                    <table>
                        <caption>Address</caption>
                        <tr class="header">
                            <th>Street</th>
                            <th>Cross Street</th>
                            <th>City</th>
                            <th>State</th>
                            <th>Zip Code</th>
                        </tr>
                        <tr>
                            <td>${sessionScope.get("student").getAddress().getStreet()}</td>
                            <td>${sessionScope.get("student").getAddress().getCrossStreet()}</td>
                            <td>${sessionScope.get("student").getAddress().getCity()}</td>
                            <td>${sessionScope.get("student").getAddress().getState()}</td>
                            <td>${sessionScope.get("student").getAddress().getZipCode()}</td>
                        </tr>
                    </table>
                </div>

                <div class="contact">
                    <table>
                        <caption>Contact</caption>
                        <tr class="header">
                            <th>College Email</th>
                            <th>Email</th>
                            <th>Phone</th>
                        </tr>
                        <tr>
                            <td>${sessionScope.get("student").getContact().getCollegeEmail()}</td>
                            <td>${sessionScope.get("student").getContact().getEmail()}</td>
                            <td>${sessionScope.get("student").getContact().getPhone()}</td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>
</body>
</html>