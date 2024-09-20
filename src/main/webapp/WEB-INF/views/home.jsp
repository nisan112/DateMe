<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.DateMe.Models.User" %> <!-- Adjust the package according to your project -->
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" href="static/css/homepage.css">
   
</head>
<body>
    <div class="container">
        <h1>Welcome User!</h1>
        
   <%
    LocalDateTime now = LocalDateTime.now();
    String formattedDateTime = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    List<User> users = (List<User>) request.getAttribute("users");
    if (users != null && !users.isEmpty()) {
        for (User user : users) {
%>
        <div id="Container">
            <div id="userContainer">
                <div id="upper">
                    <div id="profilepic">
                        <p><%= user.getFirstname() %></p>
                    </div>
                </div>
                <div id="lower">
                    <div id="nameandage">
                        <div id="name"><%= user.getFirstname() %> <%= user.getLastname() %></div>
                        <div id="age"><%= user.getAge() %></div>
                    </div>
                    <div id="address">
                        <p id="text"><%= user.getEmail() %></p>
                    </div>
                    <div id="actions">
                        <button type="button" class="reqbutt" data-email="<%= user.getEmail() %>" onClick="sendRequest(
                            '<%=(String)request.getAttribute("adminEmail") %>',
                            '<%= user.getEmail() %>',
                            '<%= formattedDateTime %>',
                            '<%= request.getContextPath() %>'
                        )">Send Request</button>
                    </div>
                </div>
            </div>
        </div>
        <%
                }
            } else { 
        %>
        <p class="no-users">No users found.</p>
        <% 
            }
        %>
    
        <div id="friendreq">
            <button onClick="reqList('<%= (String) request.getAttribute("adminEmail") %>', '<%= request.getContextPath() %>')">Requests</button>
            <div id="friendList"></div>
        </div>

        <script src="static/js/homepage.js"></script>
    </div>
</body>

</html>
