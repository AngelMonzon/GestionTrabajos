package com.registro.registro;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Side;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
public class AutocompletionTextField extends TextField {
    private final SortedSet<String> entries;
    private ContextMenu entriesPopup;

    public AutocompletionTextField() {
        super();
        this.entries = new TreeSet<>();
        this.entriesPopup = new ContextMenu();
        textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (getText().isEmpty()) {
                entriesPopup.hide();
            } else {
                List<String> filteredEntries = entries.stream()
                        .filter(e -> e.toLowerCase().contains(getText().toLowerCase()))
                        .collect(Collectors.toList());
                if (!filteredEntries.isEmpty()) {
                    populatePopup(filteredEntries);
                    if (!entriesPopup.isShowing()) {
                        entriesPopup.show(AutocompletionTextField.this, Side.BOTTOM, 0, 0);
                    }
                } else {
                    entriesPopup.hide();
                }
            }
        });
        focusedProperty().addListener((observableValue, oldValue, newValue) -> entriesPopup.hide());
    }

    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(actionEvent -> {
                setText(result);
                entriesPopup.hide();
            });
            menuItems.add(item);
        }
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);
    }

    public SortedSet<String> getEntries() {
        return entries;
    }
}
