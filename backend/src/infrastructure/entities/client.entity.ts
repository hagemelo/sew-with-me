import { ClientDomain } from "@domain/client.domain";
import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { OrderEntity } from "./order.entity";


@Entity('tb_client')
export class ClientEntity {

    @PrimaryGeneratedColumn()
    id: number;
	
    @Column()
	name: string;
    
    @Column()
	contact: string;

    @OneToMany(() => OrderEntity, (order) => order.client)
    orders: OrderEntity[]

    static fromDomain(clientDomain: ClientDomain): ClientEntity {
        const entity = new ClientEntity();
        Object.assign(entity, clientDomain)
        return entity;
    }
    
    toDomain(): ClientDomain {
        return new ClientDomain(this);
    }
}