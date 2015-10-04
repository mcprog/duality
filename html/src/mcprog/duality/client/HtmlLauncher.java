package mcprog.duality.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import java.sql.Ref;

import mcprog.duality.Duality;
import mcprog.duality.library.Reference;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Reference.W_WIDTH, Reference.W_HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Duality();
        }
}