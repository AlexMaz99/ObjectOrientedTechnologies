package command;

import model.Account;
import model.Transaction;

import java.util.List;

public class RemoveTransactionsCommand implements Command {
    List<Transaction> transactionToRemove;
    Account account;

    public RemoveTransactionsCommand(List<Transaction> transactionToRemove, Account account) {
        this.transactionToRemove = transactionToRemove;
        this.account = account;
    }

    @Override
    public void execute() {
        transactionToRemove.forEach(account::removeTransaction);
    }

    @Override
    public String getName() {
        if (transactionToRemove.size() > 1)
            return "Removed " + transactionToRemove.size() + " transactions";
        return "Removed transaction: " + transactionToRemove.toString();
    }

    @Override
    public void undo() {
        transactionToRemove.forEach(account::addTransaction);
    }

    @Override
    public void redo() {
        execute();
    }
}
