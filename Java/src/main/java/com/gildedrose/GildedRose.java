package com.gildedrose;

public class GildedRose {
    private Item[] items;

    // Constructeur pour initialiser le tableau d'items
    public GildedRose(Item[] items) {
        this.items = items;
    }

    // Méthode pour mettre à jour la qualité de tous les items
    public void updateQuality() {
        // Parcourir chaque item dans le tableau items
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    // Méthode pour mettre à jour la qualité d'un seul item
    private void updateItemQuality(Item item) {
        // Vérifier le type d'item et mettre à jour sa qualité en conséquence
        if (isNormalItem(item)) {
            decreaseQuality(item);
        } else if (isAgedBrie(item)) {
            increaseQuality(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePass(item);
        }

        // Diminuer sellIn pour tous les items sauf Sulfuras
        if (!isSulfuras(item)) {
            item.setSellIn(item.getSellIn() - 1);
        }

        // Mettre à jour la qualité si sellIn est passé
        if (item.getSellIn() < 0) {
            if (isNormalItem(item)) {
                decreaseQuality(item);
            } else if (isAgedBrie(item)) {
                increaseQuality(item);
            } else if (isBackstagePass(item)) {
                item.setQuality(0);
            }
        }
    }

    // Méthode d'aide pour vérifier si un item est un item normal
    private boolean isNormalItem(Item item) {
        return !isAgedBrie(item) && !isBackstagePass(item) && !isSulfuras(item);
    }

    // Méthode d'aide pour vérifier si un item est Aged Brie
    private boolean isAgedBrie(Item item) {
        return item.getName().equals("Aged Brie");
    }

    // Méthode d'aide pour vérifier si un item est Backstage Pass
    private boolean isBackstagePass(Item item) {
        return item.getName().equals("Backstage passes to a TAFKAL80ETC concert");
    }

    // Méthode d'aide pour vérifier si un item est Sulfuras
    private boolean isSulfuras(Item item) {
        return item.getName().equals("Sulfuras, Hand of Ragnaros");
    }

    // Méthode d'aide pour augmenter la qualité d'un item
    private void increaseQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    // Méthode d'aide pour diminuer la qualité d'un item
    private void decreaseQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    // Méthode d'aide pour mettre à jour la qualité pour Backstage Pass
    private void updateBackstagePass(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
            if (item.getSellIn() < 11 && item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
            if (item.getSellIn() < 6 && item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
        }
    }
    public Item[] getItems() {
        return items;
    }
}

