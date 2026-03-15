package bean;

import entity.User;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Named("userBean")
@ViewScoped
public class UserBean implements Serializable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistUnit");
    private EntityManager em = emf.createEntityManager();

    private User user = new User();
    private List<User> userList;

    private User selectedUser;
    private boolean editMode = false;

    public UserBean() {
        loadUsers();
    }

    public void loadUsers() {
        userList = em.createQuery("select u from User u", User.class).getResultList();
    }

    public void addUser() {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        user = new User();
        loadUsers();
    }



    public void deleteUser(User u) {
        em.getTransaction().begin();
        User toDelete = em.find(User.class, u.getId());
        if (toDelete != null) {
            em.remove(toDelete);
        }
        em.getTransaction().commit();
        loadUsers();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Utilisateur supprimé"));
    }

    public void selectUser(User u) {
        selectedUser = u;
        editMode = true;
    }
    public void updateUser() {
        em.getTransaction().begin();
        em.merge(selectedUser);
        em.getTransaction().commit();
        editMode = false;
        loadUsers();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Utilisateur modifié"));
    }



    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getSelectedUser() {
        return selectedUser;
    }
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public boolean isEditMode() {
        return editMode;
    }
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}