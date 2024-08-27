import * as yup from "yup";
import { MessageCategory } from "./definitions";

export const messageSchema = yup.object().shape({
  category: yup
    .string()
    .oneOf(Object.values(MessageCategory))
    .required("Category has to be valid"),
  message: yup.string().required("Message can't be empty"),
});
