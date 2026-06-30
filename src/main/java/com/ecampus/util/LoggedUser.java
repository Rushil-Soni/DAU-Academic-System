package com.ecampus.util;
import java.io.Serializable;
import com.ecampus.model.Users;

public class LoggedUser implements Serializable {
    private Users user;

    public LoggedUser(Users user){
        this.user=user;
    }

    public Long getUid(){
        return user.getUid();
    }

    public String getUnivId() {
        return user.getUnivId();
    }

    public Long getStdid() {
        if(!this.isStudent()){
            throw new IllegalStateException("User is not a student");
        }
        return user.getStdid();
    }

    public String getUname() {
        return user.getUname();
    }

    public String getUfullname() {
        return user.getUfullname();
    }

    public String getUrole() {
        return user.getUrole();
    }

    public String getUemail() {
        return user.getUemail();
    }

    public boolean isStudent(){
        if (UserRoles.STUDENT.equals(this.user.getUrole())) {
            return true;
        }

        return false;
    }

    public boolean isFaculty(){
        if (UserRoles.FACULTY.equals(this.user.getUrole())) {
            return true;
        }

        return false;
    }
    public boolean isDean(){
        if (UserRoles.DEAN.equals(this.user.getUrole())) {
            return true;
        }

        return false;
    }
    public boolean isRegistrar(){
        if (UserRoles.REGISTRAR.equals(this.user.getUrole())) {
            return true;
        }

        return false;
    }
    public boolean isAdmin(){
        if (UserRoles.ADMIN.equals(this.user.getUrole())) {
            return true;
        }

        return false;
    }

}
