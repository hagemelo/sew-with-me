import { MaterialDomain } from "@domain/material.domain"
import { Unit } from "@domain/models/unit.model"
import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm"
import { MaterialUsedEntity } from "./material-used.entity"

@Entity('tb_material')
export class MaterialEntity {

    @Column({name: 'mat_id'})
    @PrimaryGeneratedColumn()
        id: number
        
    @Column()
        name: string
    @Column()
        describe: string
    @Column({
        type: 'enum', name: 'unit', enum: Unit, default: Unit.M
      })
        unit: Unit

    @OneToMany(() => MaterialUsedEntity, (materialUsed) => materialUsed.material)
        materialUsed: MaterialUsedEntity[]


    static fromDomain(materialDomain: MaterialDomain): MaterialEntity {     
        const entity = new MaterialEntity();
        Object.assign(entity, materialDomain)
        return entity;
    }

    toDomain(): MaterialDomain {
        return new MaterialDomain(this);
    }
}