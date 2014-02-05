<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title> Registration</title>
    </head>
    <body>
        
        <form name="test" action = "LocalServlet" method="POST" enctype="multipart/formdata">

                   <table border="0">
                <thead>
                    <tr>
                        <th>Register</th>
                      <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>First Name:</td>
                        <td><input type="text" name="fname" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><input type="text" name="lname" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" value="" size="50"/>      </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td> <input type="submit" value="submit" name="submit" /> </td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>