package by.it.academy.solidexample.interfacesegregation

interface Drawer {
    fun drawChart()
    fun drawCustomView()
    fun drawShape()
}

interface ChartDrawer {
    fun drawChart()
}

interface ShapeDrawer {
    fun drawShape()
}