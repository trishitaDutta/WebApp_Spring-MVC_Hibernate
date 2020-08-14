<%--
  Created by IntelliJ IDEA.
  User: Trishita Dutta
  Date: 17-07-2020
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>

<%--need to create a welcome page.--%>
<%--Inside Web.xml file, there is a tag to set up the welcome file(index.jsp or index.html)--%>
<%--in this index.jsp file we just set a link for our welcome page so that whenever we run our application, it will render to the page we set over here--%>
<% response.sendRedirect("customer/list"); %>
