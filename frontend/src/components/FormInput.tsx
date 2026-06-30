interface FormInputProps {
  label: string;
  name: string;
  id: string;
  type: string;
}

function FormInput({ label, name, id, type }: FormInputProps) {
  return (
    <div>
      <label htmlFor={id} className="text-white">
        {label}
      </label>
      <input
        name={name}
        id={id}
        type={type}
        className="
         w-full
          border-2 border-white/40 rounded
          py-2 px-3
          bg-transparent text-white
          transition-colors duration-200
          outline-2
          outline-white/0
          input
"
      />
    </div>
  );
}

export default FormInput;
