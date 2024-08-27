export enum MessageCategory {
  Sports = "Sports",
  Movies = "Movies",
  Finance = "Finance",
}
export interface Message {
  category: MessageCategory;
  message: string;
}
export type HistoryRecord = {
  id: number;
  messageText: string;
  messageCategory: MessageCategory;
  notificationType: string;
  userName: string;
  userEmail: string;
  userPhone: string;
  timestamp: Date;
};
