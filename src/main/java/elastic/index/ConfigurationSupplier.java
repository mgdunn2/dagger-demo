package elastic.index;

import twitter4j.conf.ConfigurationBuilder;

import java.util.function.Supplier;

public class ConfigurationSupplier implements Supplier<ConfigurationBuilder> {
    @Override
    public ConfigurationBuilder get() {
        return new ConfigurationBuilder()
            .setOAuthConsumerKey("FExPjtJFCZPXBUL0ac8AE1b2o")
            .setOAuthConsumerSecret("WeSHYX84pn7ex5g0G5oZu6x6jGLfAw06iM864iJxXzbRYv1H9S")
            .setOAuthAccessToken("1114233277664190464-PkfNMLHWMvdtVgfAuTD6RBrfLUNENA")
            .setOAuthAccessTokenSecret("qfuuAO01ZhjVd4BasZY1JQmzGoYgB2sXG3nMFoSY2tm97");
    }
}
