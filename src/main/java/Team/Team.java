package Team;

public class Team {

   private final Integer m_id;
    private  String m_name;

    public Team(Integer p_id, String p_name) {
        this.m_id = p_id;
        this.m_name = p_name;
    }

    public Integer getId() {
        return m_id;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        this.m_name = name;
    }
}
