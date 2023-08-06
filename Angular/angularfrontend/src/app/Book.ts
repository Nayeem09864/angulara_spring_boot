export enum BookType {
    FREE,
    PREMIUM,
}

export interface IBook {
  id?: number;
  title?: string;
  author?: string;
  bookType?: BookType;
}
export class Book implements IBook{
  constructor(
    public id?:number,
    public title?: string,
    public author?: string,
    public bookType?: BookType
  ) {}

}
