package damian.hms.gui;

public interface FormInterface {
	abstract void updateList();
	abstract void refillForm(int index);
	abstract void createNew();
	abstract void save(int index);
	abstract void delete(int index);
}
