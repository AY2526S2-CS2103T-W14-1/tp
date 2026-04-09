package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.session.SessionEntry;

/**
 * Panel containing the list of sessions for the currently selected pet.
 */
public class SessionListPanel extends UiPart<Region> {

    private static final String FXML = "SessionListPanel.fxml";

    @FXML
    private Label sessionPanelTitle;

    @FXML
    private ListView<SessionEntry> sessionListView;

    /**
     * Creates a {@code SessionListPanel} bound to the given observable session list.
     * The panel updates automatically whenever the list changes.
     */
    public SessionListPanel(ObservableList<SessionEntry> sessionList) {
        super(FXML);
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new SessionListViewCell());
        Label placeholder = new Label("No sessions to display.\nUse addsession to schedule one.");
        placeholder.getStyleClass().add("empty-state-label");
        sessionListView.setPlaceholder(placeholder);
        sessionPanelTitle.getStyleClass().add("section-title");
        sessionPanelTitle.setText("Sessions");
    }

    /**
     * Updates the title shown at the top of the session panel.
     */
    public void setTitle(String title) {
        sessionPanelTitle.setText(title);
    }

    /**
     * Custom {@code ListCell} that displays a {@code Session} using a {@code SessionCard}.
     */
    class SessionListViewCell extends ListCell<SessionEntry> {
        @Override
        protected void updateItem(SessionEntry entry, boolean empty) {
            super.updateItem(entry, empty);
            if (empty || entry == null) {
                setGraphic(null);
                setText(null);
            } else {
                VBox section = new VBox();
                if (entry.sessionIndex() == 1) {
                    Label sectionHeader = new Label(String.format("%s's %s", entry.ownerName(), entry.petName()));
                    sectionHeader.getStyleClass().add("session-section-header");
                    sectionHeader.setWrapText(true);
                    VBox.setMargin(sectionHeader, new Insets(10, 0, 6, 10));
                    section.getChildren().add(sectionHeader);
                }
                section.getChildren().add(new SessionCard(entry, entry.sessionIndex()).getRoot());
                setGraphic(section);
            }
        }
    }
}
