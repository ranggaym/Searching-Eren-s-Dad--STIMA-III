<%-- 
    Document   : Home
    Created on : Dec 1, 2013, 6:11:15 PM
    Author     : user
--%>

<html>
<head>
</head>

<body>
<h3> My Sentiment Analytics </h3>
<form action = "proses" method = "get"> <!-- atau analyze.jsp -->
        <i>Keyword</i> pencarian <i>tweets</i> : <br> <input type="text" name="tweetkeyword" onchange="validator();"><br>
        <i>Keyword</i> sentimen positif : <br> <input type="text" name="positivesentiments" onchange="validator();"><br>
        <i>Keyword</i> sentimen negatif : <br> <input type="text" name="negativesentiments" onchange="validator();"><br>

        <input type="radio" name="algotype" value="bm" checked> Boyer-Moore </br>
        <input type="radio" name="algotype" value="kmp"> KMP </br>
        <input id="submit_button" type="submit" value="Analisis"> </br>
        
</form>
</body>
</html>