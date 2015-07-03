package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(3, "com.ddup.dbdata");
        Entity goal = addGoal(schema);
        Entity goalCategory = addGoalCategory(schema, goal);
        addActions(schema, goal, goalCategory);
        new DaoGenerator().generateAll(schema, args[0]);
    }

    private static Entity addGoal(Schema schema) {
        Entity goal = schema.addEntity("Goal");
        goal.setHasKeepSections(true);
        goal.implementsSerializable();
        goal.addIdProperty();
        goal.addStringProperty("desc");
        goal.addLongProperty("createTime");
        goal.addBooleanProperty("invalid");
        return goal;
    }

    private static Entity addGoalCategory(Schema schema, Entity goal) {
        Entity goalCategory = schema.addEntity("GoalCategory");
        goalCategory.setHasKeepSections(true);
        goalCategory.implementsSerializable();
        goalCategory.addIdProperty();
        goalCategory.addStringProperty("desc");
        goalCategory.addLongProperty("createTime");
        goalCategory.addBooleanProperty("invalid");
        goalCategory.addStringProperty("timePeriod");
        goalCategory.addToMany(goal, goal.addLongProperty("categoryId").getProperty());
        return goalCategory;
    }

    private static void addActions(Schema schema, Entity goal, Entity goalCategory){
        Entity action = schema.addEntity("Action");
        action.addIdProperty();
        action.addLongProperty("time");
        action.addStringProperty("status");
        action.addStringProperty("timePeriod");
        action.addToOne(goal, action.addLongProperty("goalId").getProperty());
        action.addToOne(goalCategory, action.addLongProperty("goalCategoryId").getProperty());
    }
}
