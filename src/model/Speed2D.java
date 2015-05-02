package model;

/**
 * Вектор скорости в двумерном пространстве
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Speed2D {

    private double _x;
    private double _y;

    /**
     * Перечисление осей
     *
     * @author Елена
     */
    public enum Axis {

        X,
        Y,
        Z;
    }

    /**
     * Создаёт экземпляр вектора скорости на декартовой плоскости
     */
    public Speed2D() {
        this(0, 0);
    }

    /**
     * Создаёт экземпляр вектора скорости на декартовой плоскости
     *
     * @param xspeed Компонента вектора по x
     * @param yspeed Компонента вектора по y
     */
    public Speed2D(double xspeed, double yspeed) {
        _x = xspeed;
        _y = yspeed;
    }

    public Speed2D clone() {

        return new Speed2D(_x, _y);
    }

    @Override
    public boolean equals(Object other) {

        Speed2D oth = (Speed2D) other;
        return oth._x == this._x && oth._y == this._y;
    }

    public double x() {
        return _x;
    }

    public double y() {
        return _y;
    }

    public Speed2D flipHorizontal() {
        return new Speed2D(-_x, _y);
    }

    public Speed2D flipVertical() {
        return new Speed2D(_x, -_y);
    }

    /**
     * Вычислить сумму векторов
     *
     * @param other другой вектор скорости
     * @return результирующий вектор скорости
     */
    public Speed2D sum(Speed2D other) {
        return new Speed2D(this.x() + other.x(), this.y() + other.y());
    }

    /**
     * Отразить вектор
     *
     * @param axis ось, относительно которой будет происходить отражение
     * @return результирующий вектор
     */
    public Speed2D reflect(Axis axis) {
        Speed2D result = this.clone();
        switch (axis) {
            case X:
                result._y = -this.y();
                break;
            case Y:
                result._x = -this.x();
                break;
            case Z:
                result._x = -this.x();
                result._y = -this.y();
                break;
        }
        return result;
    }
}
