import { Client } from "@domain/models/client.model";
import { Column, Entity, JoinColumn, ManyToOne, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { OrderDomain } from "@domain/order.domain";
import { OrderStatus } from "@domain/models/order-status.model";
import { Sew } from "@domain/models/sew.model";
import { SewEntity } from "./sew.entity";
import { ClientEntity } from "./client.entity";

@Entity('tb_order')
export class OrderEntity {

    @PrimaryGeneratedColumn()
    id: number;

    @ManyToOne(() => ClientEntity, (client) => client.orders)
    @JoinColumn({ name: 'client_id' })
    client: ClientEntity;

    @Column({ type: 'date' })
    created_at: Date;

    @Column()
	value: number;

    @Column({ type: 'enum', name: 'status', enum: OrderStatus })
	status: OrderStatus;

    @Column({ type: 'date' , name: 'delivery_forecast' })
    deliveryForecast: Date;

    @OneToMany(() => SewEntity, (sew) => sew.order)
    sews: SewEntity[]

    static fromDomain(orderDomain: OrderDomain): OrderEntity {     
        const entity = new OrderEntity();
        Object.assign(entity, orderDomain)
        return entity;
    }

    toDomain(): OrderDomain {
        return new OrderDomain(this);
    }
}