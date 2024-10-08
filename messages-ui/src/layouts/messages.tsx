import React, { FC } from "react";
//Gonna use formik on this one
import { Formik, Field } from "formik";
import { messageSchema } from "../lib/message-schema";
import { MessageCategory } from "../lib/definitions";
import { StyledForm } from "../components/messages-styled";

const messageCategoryKeys = Object.keys(MessageCategory);
const callMessageApi = (message: string, category: MessageCategory) => {
  fetch("http://localhost:8080/messages", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ message, category }),
  })
    .then((res) => {
      if (!res.ok) {
        throw new Error("Failed to send message");
      }
      alert("Message sent");
    })
    .catch((error) => {
      if (error.message === "Failed to fetch") {
        alert("Failed to send message, API could be off");
      }
    });
};
const Messages: FC = () => {
  return (
    <div>
      <h1>Input your message</h1>
      <Formik
        initialValues={{ category: MessageCategory.Sports, message: "" }}
        validationSchema={messageSchema}
        onSubmit={(values, { setSubmitting }) => {
          callMessageApi(values.message, values.category);
          setSubmitting(false);
        }}
      >
        {({
          errors,
          touched,
          handleSubmit,
          isSubmitting,
          /* and other goodies */
        }) => (
          <StyledForm onSubmit={handleSubmit}>
            <Field name="category" placeholder="Category" as="select">
              {messageCategoryKeys.map((category) => (
                <option key={category} value={category}>
                  {category}
                </option>
              ))}
            </Field>
            {errors.category && touched.category ? (
              <div>{errors.category}</div>
            ) : null}
            <Field name="message" placeholder="Message" as="textarea" />
            {errors.message && touched.message ? (
              <div>{errors.message}</div>
            ) : null}
            <button type="submit" disabled={isSubmitting}>
              Submit
            </button>
          </StyledForm>
        )}
      </Formik>
    </div>
  );
};
export default Messages;
