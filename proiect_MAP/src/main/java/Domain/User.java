package Domain;

import java.time.LocalDate;
import java.util.UUID;
import Helpers.*;
import Observers.Observer;
import java.util.Date;
import java.util.Calendar;


public class User implements Observer {
    private UUID userId;
    private String username;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private Membership membership;
    private String userType;

    @Override
    public void update(Subject subject) {
        if (subject instanceof Artist artist) {
            System.out.println(username + " received an update: Artist:  " + artist.getName() + " added a new song!");
        }
    }

    public User(){}

    public User(String username, String email, String password, LocalDate dateOfBirth) {

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if(!GeneralHelper.isValidEmail(email)){
            throw new IllegalArgumentException("Email not valid");
        }

        if (password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if(!GeneralHelper.isValidPassword(password)){
            throw new IllegalArgumentException("Password must be 8 characters long, contain a special character, contain a digit and contain an upper and lower case character");
        }

        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth is not valid");
        }

        this.userId = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.membership = null;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Membership getMembership() {
        return membership;
    }

    public UUID getId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void renewMembership(PaymentStrategy paymentMethode) {
        Date currentDate = new Date();
        Date membershipEndDate = this.membership.getEndDate();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        paymentProcessor.setPaymentStrategy(paymentMethode);
        paymentProcessor.performPayment(30);
        if (currentDate.equals(membershipEndDate) || membershipEndDate == null) {
            Calendar calendar = Calendar.getInstance();
            if (membershipEndDate == null)
                calendar.setTime(currentDate);
            else
                calendar.setTime(membershipEndDate);

            calendar.add(Calendar.DAY_OF_MONTH, 31);
            Date newEndDate = calendar.getTime();
            this.membership.setStartDate(currentDate);
            this.membership.setEndDate(newEndDate);
            System.out.println("Membership renewed. New end date: " + newEndDate);
        } else {
            System.out.println("Membership not renewed. Current date does not match the end date.");
        }
    }

    @Override
    public String toString() {
        return "userId =" + userId +
                ", username ='" + username + '\'' +
                ", email ='" + email + '\'' +
                ", dateOfBirth =" + dateOfBirth +
                ", type =" + userType +
                "\n";
    }
}