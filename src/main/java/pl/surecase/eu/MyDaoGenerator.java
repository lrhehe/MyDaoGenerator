package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.ddup.dbdata");
        Entity goal = addGoal(schema);
        addGoalCategory(schema, goal);
        new DaoGenerator().generateAll(schema, args[0]);
    }

    private static Entity addGoal(Schema schema) {
        Entity goal = schema.addEntity("Goal");
        goal.setHasKeepSections(true);
        goal.implementsSerializable();
        goal.addIdProperty();
        goal.addShortProperty("desc");
        goal.addLongProperty("createTime");
        goal.addStringProperty("status");
        return goal;
    }

    private static void addGoalCategory(Schema schema, Entity goal) {
        Entity goalCategory = schema.addEntity("GoalCategory");
        goalCategory.setHasKeepSections(true);
        goalCategory.implementsSerializable();
        goalCategory.addIdProperty();
        goalCategory.addShortProperty("desc");
        goalCategory.addLongProperty("createTime");
        goalCategory.addStringProperty("status");

        goalCategory.addToMany(goal, goal.addLongProperty("categoryId").getProperty());

    }
}
