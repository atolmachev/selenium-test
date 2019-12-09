package todo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static todo.TodoUtil.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import support.Browser;

public class TodosCan {

	WebDriver driver = Browser.launch();

	@Before
	public void setup() {
		driver.get("http://testdouble.github.io/todos/");
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void createTodo() {
		addTodo("Mow the Lawn");

		assertThat(todoAt(0).getText(), is("Mow the Lawn"));
	}

	@Test
	public void marksTodoAsDone() {
		addTodo("Eat Food");
		assertThat(isDone(todoAt(0)), is(false));

		markTodoDone(todoAt(0));

		assertThat(isDone(todoAt(0)), is(true));
	}

	@Test
	public void deleteATodo() {
		addTodo("Thing");

		deleteTodo(todoAt(0));

		assertThat(todoAt(0, true), is(nullValue()));
	}

	@Test
	public void clearCompletedTodos() {
		addTodo("Do Stuff");
		markTodoDone(todoAt(0));

		clearDoneTodos();

		assertThat(todoAt(0, true), is(nullValue()));
	}
}