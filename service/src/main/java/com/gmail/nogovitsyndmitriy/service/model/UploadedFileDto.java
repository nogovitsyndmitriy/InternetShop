package com.gmail.nogovitsyndmitriy.service.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadedFileDto {

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<ItemDto> items;

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
