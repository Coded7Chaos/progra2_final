package com.transporte.gui;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.painter.Painter;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

public class ZonePainter implements Painter<JXMapViewer> {
    private final List<GeoPosition> zonePoints;
    private final Color color;

    public ZonePainter(List<GeoPosition> zonePoints, Color color) {
        this.zonePoints = zonePoints;
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 128); // Color transparente
    }

    @Override
    public void paint(Graphics2D g, JXMapViewer mapViewer, int width, int height) {
        g = (Graphics2D) g.create();
        g.setColor(color);
        g.setStroke(new BasicStroke(2));

        Path2D path = new Path2D.Double();
        boolean first = true;

        for (GeoPosition geoPosition : zonePoints) {
            Point2D point = mapViewer.getTileFactory().geoToPixel(geoPosition, mapViewer.getZoom());
            if (first) {
                path.moveTo(point.getX(), point.getY());
                first = false;
            } else {
                path.lineTo(point.getX(), point.getY());
            }
        }

        path.closePath();
        g.fill(path); // Rellenar el polígono
        g.draw(path); // Dibujar el borde del polígono
        g.dispose();
    }
}
