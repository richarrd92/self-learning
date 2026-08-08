/*
  Warnings:

  - The primary key for the `Image` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `compressedSize` on the `Image` table. All the data in the column will be lost.
  - You are about to drop the column `description` on the `Image` table. All the data in the column will be lost.
  - You are about to drop the column `height` on the `Image` table. All the data in the column will be lost.
  - You are about to drop the column `originalSize` on the `Image` table. All the data in the column will be lost.
  - You are about to drop the column `title` on the `Image` table. All the data in the column will be lost.
  - You are about to drop the column `updatedAt` on the `Image` table. All the data in the column will be lost.
  - You are about to drop the column `width` on the `Image` table. All the data in the column will be lost.
  - The `id` column on the `Image` table would be dropped and recreated. This will lead to data loss if there is data in the column.
  - Added the required column `format` to the `Image` table without a default value. This is not possible if the table is not empty.
  - Added the required column `imageUrl` to the `Image` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "Image" DROP CONSTRAINT "Image_pkey",
DROP COLUMN "compressedSize",
DROP COLUMN "description",
DROP COLUMN "height",
DROP COLUMN "originalSize",
DROP COLUMN "title",
DROP COLUMN "updatedAt",
DROP COLUMN "width",
ADD COLUMN     "format" TEXT NOT NULL,
ADD COLUMN     "imageUrl" TEXT NOT NULL,
DROP COLUMN "id",
ADD COLUMN     "id" SERIAL NOT NULL,
ADD CONSTRAINT "Image_pkey" PRIMARY KEY ("id");
