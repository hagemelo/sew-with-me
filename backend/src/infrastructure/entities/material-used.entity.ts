import { Unit } from "@domain/models/unit.model"
import { Column, Entity, JoinColumn, ManyToOne, PrimaryGeneratedColumn } from "typeorm"
import { MaterialEntity } from "./material.entity"
import { MaterialUsedDomain } from "@domain/material-used.domain"
import { SewEntity } from "./sew.entity"

@Entity('tb_material_used')
export class MaterialUsedEntity {

    @PrimaryGeneratedColumn()
        id: number

    @Column()
        quantity: number

    @Column({
        type: 'enum', name: 'unit', enum: Unit, default: Unit.M
      })
        unit: Unit

    @ManyToOne(() => MaterialEntity, (material) => material.materialUsed)
    @JoinColumn({ name: 'material_id' })
        material: MaterialEntity

    @ManyToOne(() => SewEntity, (sew) => sew.materials)
    @JoinColumn({ name: 'sew_id' })
        sew: SewEntity

    static fromDomain(materialUsedDomain: MaterialUsedDomain): MaterialUsedEntity {     
        const entity = new MaterialUsedEntity();
        Object.assign(entity, materialUsedDomain)
        return entity;
    }

    toDomain(): MaterialUsedDomain {
        return new MaterialUsedDomain(this);
    }

}