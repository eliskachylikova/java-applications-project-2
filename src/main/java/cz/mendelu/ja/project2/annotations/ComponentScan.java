package cz.mendelu.ja.project2.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// annotation can be placed to an item multiple times and these values are stored in ComponentScans.class
@Repeatable(ComponentScans.class) public @interface ComponentScan {
    public String value();
}
