package ntut.csie.sslab.kanban.workflow.entity;

public class SwimLane extends Lane {

    public SwimLane (
            String id,
            String workflowId,
            Lane parent,
            String name,
            WipLimit wipLimit,
            int order,
            LaneType type) {

        super(id, workflowId, parent, name, wipLimit, order, type);
        setLayout(LaneLayout.Horizontal);
    }
}
