# INJECT-2: Avoid dynamic SQL
It is well known that dynamically created SQL statements including untrusted input are subject to command injection. This often takes the form of supplying an input containing a quote character (') followed by SQL. Avoid dynamic SQL.

For parameterised SQL statements using Java Database Connectivity (JDBC), use java.sql.PreparedStatement or java.sql.CallableStatement instead of java.sql.Statement. In general, it is better to use a well-written, higher-level library to insulate application code from SQL. When using such a library, it is not necessary to limit characters such as quote ('). If text destined for XML/HTML is handled correctly during output (Guideline 3-3), then it is unnecessary to disallow characters such as less than (<) in inputs to SQL.

An example of using PreparedStatement correctly:

        String sql = "SELECT * FROM User WHERE userId = ?"; 
        PreparedStatement stmt = con.prepareStatement(sql); 
        stmt.setString(1, userId); 
        ResultSet rs = prepStmt.executeQuery();
