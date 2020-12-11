package com.academy.it.solidexample.interfacesegregation

class DrawerImpl: ChartDrawer, ShapeDrawer {
    override fun drawChart() {
        TODO("Not yet implemented")
    }

    override fun drawShape() {
        TODO("Not yet implemented")


        val textDrawer = object: TexxDrawer() {
            override fun drawChart() {
                super.drawChart()
            }
        }

    }
}