package sample.web.ui.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue
    private Long id;

    private double result;


    @Transient
    private GradeState state = new GradeStateNotEntered();

    private String currentState = this.getState().getClass().getSimpleName();

    //@OneToOne(cascade = {CascadeType.MERGE}) old
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Exam exam;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private User student;

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        this.state = determineCurrentState();
        state.next(this);
        this.currentState = this.getState().getClass().getSimpleName();
    }

    public void printStatus() {
        state.printStatus();
    }

    public GradeState determineCurrentState(){
        GradeState gradeState = null;
        if(this.getCurrentState() == "GradeStateNotEntered") {
            gradeState = new GradeStateNotEntered();
        } else if (this.getCurrentState() == "GradeStateConcept"){
            gradeState = new GradeStateConcept();
        }else if(this.getCurrentState() == "GradeStateFinal") {
            gradeState = new GradeStateFinal();
        }
        return gradeState;
    }


}
