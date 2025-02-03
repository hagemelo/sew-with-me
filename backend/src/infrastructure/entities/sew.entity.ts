import { SewStatus } from "@domain/models/sew-status.model";
import { TypeSew } from "@domain/models/type-sew.model";
import { Column, Entity, JoinColumn, ManyToOne, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { MaterialUsedEntity } from "./material-used.entity";
import { SewDomain } from "@domain/sew.domain";
import { OrderEntity } from "./order.entity";

@Entity('tb_sew')
export class SewEntity {
    
    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    describe: string
    
    @Column({
        type: 'enum', name: 'type', enum: TypeSew, default: TypeSew.REPARO
      })
    type: TypeSew;

    @Column({
        type: 'enum', name: 'status', enum: SewStatus, default: SewStatus.NAO_INICIADO
      })
    status: SewStatus;

    @Column()
    value: number;

    @Column({ type: 'date' })
    deliveryForecast: Date;

    @OneToMany(() => MaterialUsedEntity, (materialUsed) => materialUsed.sew)
    materials: MaterialUsedEntity[];

    @ManyToOne(() => OrderEntity, (order) => order.sews)
    @JoinColumn({ name: 'order_id' })
    order: OrderEntity;

    static fromDomain(sewDomain: SewDomain): SewEntity {     
        const entity = new SewEntity();
        Object.assign(entity, sewDomain)
        return entity;
    }

    toDomain(): SewDomain {
        return new SewDomain(this);
    }
}