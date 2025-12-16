    package ec.edu.espe.contactstbook.model;

    import java.util.ArrayList;
    import java.util.Date;

    /**
     *
     * @author Josue Rojas
     */
    public class Contact {

        private int id;
        private String firstName;
        private String lastName;
        private Date birthDate;
        private int age;
        private String typeOfContact; //Family, Friend, Job, Unknown
        private String sex; //male, female
        private ArrayList<String> hobbies;
        private String comments;

        public Contact(int id, String firstName, String lastName, Date birthDate, int age, String typeOfContact, String sex, ArrayList<String> hobbies, String comments) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
            this.age = age;
            this.typeOfContact = typeOfContact;
            this.sex = sex;
            this.hobbies = hobbies;
            this.comments = comments;
        }

        @Override
        public String toString() {
            return "Contact{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + ", age=" + age + ", typeOfContact=" + typeOfContact + ", sex=" + sex + ", hobbies=" + hobbies + ", comments=" + comments + '}';
        }


        public Contact() {

        }

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @return the firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * @param firstName the firstName to set
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * @return the brithDate
         */
        public Date getBirthDate() {
            return birthDate;
        }

        /**
         * @param birthDate the brithDate to set
         */
        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        /**
         * @return the lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * @param lastName the lastName to set
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         * @return the age
         */
        public int getAge() {
            return age;
        }

        /**
         * @param age the age to set
         */
        public void setAge(int age) {
            this.age = age;
        }

        /**
         * @return the typeOfContact
         */
        public String getTypeOfContact() {
            return typeOfContact;
        }

        /**
         * @param typeOfContact the typeOfContact to set
         */
        public void setTypeOfContact(String typeOfContact) {
            this.typeOfContact = typeOfContact;
        }

        /**
         * @return the sex
         */
        public String getSex() {
            return sex;
        }

        /**
         * @param sex the sex to set
         */
        public void setSex(String sex) {
            this.sex = sex;
        }

        /**
         * @return the hobbies
         */
        public ArrayList<String> getHobbies() {
            return hobbies;
        }

        /**
         * @param hobbies the hobbies to set
         */
        public void setHobbies(ArrayList<String> hobbies) {
            this.hobbies = hobbies;
        }

        /**
         * @return the comments
         */
        public String getComments() {
            return comments;
        }

        /**
         * @param comments the comments to set
         */
        public void setComments(String comments) {
            this.comments = comments;
        }

    }
