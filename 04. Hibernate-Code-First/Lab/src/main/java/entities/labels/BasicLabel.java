package entities.labels;

import entities.shampoos.BasicShampoo;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel implements Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String subtitle;

    @OneToOne(mappedBy = "basicLabel", targetEntity = BasicShampoo.class, cascade = CascadeType.ALL)
    private BasicShampoo basicShampoo;

    public BasicLabel(){};

    public BasicLabel(String title, String subtitle){
        this.title = title;
        this.subtitle = subtitle;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getSubtitle() {
        return null;
    }

    @Override
    public void setSubtitle(String subtitle) {

    }
}
