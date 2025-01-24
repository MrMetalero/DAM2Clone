


productos(nombre_producto:varchar,precio:double,fk_nombre_cliente:varchar){
VNN: nombre_producto
VNN: precio
Caj: fk_nombre_cliente clientes(nombre_cliente)
}

cliente(nombre_cliente:varchar,telefono:double){
VNN: nombre_cliente
VNN: telefono
}

formulas(fk_nombre_producto:varchar,fecha_comercializacion:date){
VNN: fk_nombre_producto
VNN: fecha_comercializacion
Caj: fk_nombre_producto productos(nombre_producto)
}

componentes(fk_nombre_producto:varchar){
VNN fk_nombre_producto
Caj: fk_nombre_producto productos(nombre_producto)
}

piezas(nombre_pieza:varchar,peso:double,fk_nombre_componente:varchar){
VNN: nombre_pieza
VNN: peso
VNN: fk_nombre_componente
Caj: fk_nombre_componente componentes(fk_nombre_producto)
}



RI1: El precio de un producto nunca podrá ser inferior a 3€ *

RI2: No puede existir un producto en la tabla Componentes y
     estar también en la tabla Fórmulas y viceversa *

RI3: No pueden haber mas de 4 registros en piezas por cada nombre_componente

RI4: Al insertar un registro en la tabla Productos, se debe de insertar un componente
     o una formula con su id *

RI5: Si se inserta un registro en componentes, debe haber al menos 1 registro con su id 
     en componentes_contienen *

RI6: Si se inserta un registro en piezas, debe haber al menos 1 registro con su id 
     en componentes_contienen 



