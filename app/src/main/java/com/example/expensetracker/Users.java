package com.example.expensetracker;

public class Users
{
    String Uname, Umail, Umobile, Uupi, Upassword;

    public Users()
    {
        //default constructor.
    }

    public Users(String Uname, String Umail, String Umobile, String Uupi, String Upassword)
    {
        this.Uname = Uname;
        this.Umail = Umail;
        this.Umobile = Umobile;
        this.Uupi = Uupi;
        this.Upassword = Upassword;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getUmail() {
        return Umail;
    }

    public void setUmail(String umail) {
        Umail = umail;
    }

    public String getUmobile() {
        return Umobile;
    }

    public void setUmobile(String umobile) {
        Umobile = umobile;
    }

    public String getUupi() {
        return Uupi;
    }

    public void setUupi(String uupi) {
        Uupi = uupi;
    }

    public String getUpassword() {
        return Upassword;
    }

    public void setUpassword(String upassword) {
        Upassword = upassword;
    }
}