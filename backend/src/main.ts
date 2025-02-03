import { NestFactory } from '@nestjs/core';
import { SewBackendModule } from './sew-backend.module';
import { initializeTransactionalContext } from 'typeorm-transactional';
import { VersioningType } from '@nestjs/common';

async function bootstrap() {
  initializeTransactionalContext();
  const app = await NestFactory.create(SewBackendModule);
  app.enableCors({
    credentials: true,
    methods: ['GET', 'POST'],
    origin: ['http://localhost:8080'],
  });
  app.enableVersioning({
    type: VersioningType.URI,
  });
  await app.listen(3000);
}
bootstrap();
