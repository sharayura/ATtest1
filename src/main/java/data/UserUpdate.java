package data;

/**
 * @author Sharapov Yuri
 */
public class UserUpdate {
    public static final String NAME_UPDATE = "morpheus";
    public static final String JOB_UPDATE = "zion resident";

    private String name;
    private String job;

    public UserUpdate(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
