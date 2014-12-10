package se.rydberg.handla.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRole {
    private Integer userRoleId;
    private String role;
    
    

    public UserRole(){
        
        
        
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_role_sequence")
    @SequenceGenerator(name = "user_role_sequence", sequenceName = "USER_ROLE_SEQUENCE")
    @Column(name="user_role_id", nullable=false)
    public Integer getUserRoleId() {
        return userRoleId;
    }


    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Column(name="ROLE")
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    
}
