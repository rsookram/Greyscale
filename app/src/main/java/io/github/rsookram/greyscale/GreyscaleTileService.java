package io.github.rsookram.greyscale;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

public class GreyscaleTileService extends TileService {

    private final GreyscaleSetting greyscaleSetting = new GreyscaleSetting(this);

    @Override
    public void onClick() {
        super.onClick();

        if (!greyscaleSetting.canChange()) {
            Toast.makeText(this, R.string.no_permission, Toast.LENGTH_LONG).show();
            return;
        }

        boolean enable = getQsTile().getState() == Tile.STATE_INACTIVE;

        greyscaleSetting.setEnabled(enable);
        setTileState(enable ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
    }

    @Override
    public void onStartListening() {
        super.onStartListening();

        boolean enabled = greyscaleSetting.isEnabled();
        setTileState(enabled ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
    }

    private void setTileState(int state) {
        Tile tile = getQsTile();
        tile.setState(state);
        tile.updateTile();
    }
}
