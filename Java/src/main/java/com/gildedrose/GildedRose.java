package com.gildedrose;

/**
 * La classe GildedRose représente le système de gestion des items dans le magasin Gilded Rose.
 * Elle permet de mettre à jour la qualité des items en fonction de règles spécifiques.
 */
public class GildedRose {
    private Item[] items;

    /**
     * Constructeur de la classe GildedRose.
     * @param items Un tableau d'items à gérer.
     */
    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * Met à jour la qualité de tous les items.
     * Cette méthode parcourt tous les items et met à jour leur qualité en fonction de règles spécifiques.
     */
    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    /**
     * Met à jour la qualité d'un seul item en fonction de ses règles spécifiques.
     * @param item L'item à mettre à jour.
     */
    private void updateItemQuality(Item item) {
        if (isNormalItem(item)) {
            decreaseQuality(item);
        } else if (isAgedBrie(item)) {
            increaseQuality(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePass(item);
        }

        if (!isSulfuras(item)) {
            item.setSellIn(item.getSellIn() - 1);
        }

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

    private boolean isNormalItem(Item item) {
        return !isAgedBrie(item) && !isBackstagePass(item) && !isSulfuras(item);
    }

    private boolean isAgedBrie(Item item) {
        return item.getName().equals("Aged Brie");
    }

    private boolean isBackstagePass(Item item) {
        return item.getName().equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.getName().equals("Sulfuras, Hand of Ragnaros");
    }

    private void increaseQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private void decreaseQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

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

    /**
     * Récupère tous les items gérés par GildedRose.
     * @return Un tableau d'items.
     */
    public Item[] getItems() {
        return items;
    }
}
