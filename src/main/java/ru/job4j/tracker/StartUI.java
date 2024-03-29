package ru.job4j.tracker;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.length) {
                out.println("Wrong input, you can select: 0 .. " + (actions.length - 1));
                continue;
            }
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.length; index++) {
            out.println(index + ". " + actions[index].name());
        }
    }

    public static void main(String[] args) {
        Log4File log = Log4File.getInstance();
        log.add("add new Item");
        log.save();

        Output out = new ConsoleOutput();
        Input input = new ValidateInput(out, new ConsoleInput());
        Store tracker = new MemTracker();
        UserAction[] actions = {
                new CreateAction(out),
                new CreateManyItems(out),
                new ShowAllItems(out),
                new EditItem(out),
                new DeleteItem(out),
                new DeleteAllItems(out),
                new ShowById(out),
                new ShowItemsByName(out),
                new ExitProgram(out),
        };
        new StartUI(out).init(input, tracker, actions);
    }
}
