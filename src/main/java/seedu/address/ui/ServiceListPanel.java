package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.service.Service;

/**
 * Panel displaying all available services and their prices.
 */
public class ServiceListPanel extends UiPart<Region> {

    private static final String FXML = "ServiceListPanel.fxml";

    @FXML
    private ListView<Service> serviceListView;

    /**
     * Creates a {@code ServiceListPanel} bound to the given observable service list.
     */
    public ServiceListPanel(ObservableList<Service> serviceList) {
        super(FXML);
        serviceListView.setItems(serviceList);
        serviceListView.setCellFactory(listView -> new ServiceListViewCell());
        Label placeholder = new Label("No services yet.\nUse addservice to build your catalogue.");
        placeholder.getStyleClass().add("empty-state-label");
        serviceListView.setPlaceholder(placeholder);
    }

    /**
     * Custom {@code ListCell} that displays a {@code Service} as "name — $price".
     */
    class ServiceListViewCell extends ListCell<Service> {
        @Override
        protected void updateItem(Service service, boolean empty) {
            super.updateItem(service, empty);
            if (empty || service == null) {
                setGraphic(null);
                setText(null);
            } else {
                setText(String.format("%-24s $%.2f", service.getName(), service.getCost()));
                getStyleClass().add("service-list-cell");
            }
        }
    }
}
