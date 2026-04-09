package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of owners.
 */
public class OwnerListPanel extends UiPart<Region> {
    private static final String FXML = "OwnerListPanel.fxml";

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code OwnerListPanel} with the given {@code ObservableList}.
     */
    public OwnerListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new OwnerListViewCell());
        Label placeholder = new Label("No owners yet.\nUse addowner to create your first record.");
        placeholder.getStyleClass().add("empty-state-label");
        personListView.setPlaceholder(placeholder);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code OwnerCard}.
     */
    class OwnerListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OwnerCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
