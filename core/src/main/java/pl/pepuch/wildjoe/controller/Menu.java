package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.core.WildJoe;
import pl.pepuch.wildjoe.core.world.GameWorld;
import pl.pepuch.wildjoe.model.MenuModel;
import pl.pepuch.wildjoe.view.MenuView;
import playn.core.PlayN;
import react.UnitSlot;

public class Menu extends StaticActor {

    public Menu(final WildJoe game) {
        model = new MenuModel();
        view = new MenuView();
        PlayN.graphics().rootLayer().add(view().layer());
        hide();

        view().start().clicked().connect(new UnitSlot() {
            @Override
            public void onEmit() {
                PlayN.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        hide();
                        game.setGameWorld(new GameWorld(game));
                        game.loadLevel(game.gameWorld().scoreboard().level());
                    }
                });
            }
        });
        view().scores().clicked().connect(new UnitSlot() {
            @Override
            public void onEmit() {
                PlayN.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        hide();
                        game.scores().show();
                    }
                });
            }
        });

    }

    public void show() {
        view().layer().setVisible(true);
    }

    public void hide() {
        view().layer().setVisible(false);
    }

    @Override
    public MenuView view() {
        return (MenuView) view;
    }

    @Override
    public MenuModel model() {
        return (MenuModel) model;
    }
}
