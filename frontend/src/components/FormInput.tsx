interface FormInputProps {
  label: string;
  name: string;
  id: string;
  type: string;
  isRequired?: boolean;
  value: string;
  onChange: React.ChangeEventHandler<HTMLInputElement>;
}

function FormInput({
  label,
  name,
  id,
  type,
  isRequired,
  value,
  onChange,
}: FormInputProps) {
  return (
    <div>
      <label htmlFor={id} className="text-white">
        {label}
      </label>
      <input
        name={name}
        id={id}
        type={type}
        required={isRequired}
        value={value}
        onChange={onChange}
        className={`
           w-full
            border-2 border-gray-500 rounded
            py-2 px-3
            bg-transparent text-white
            transition-colors duration-200
            outline-2
            outline-white/0
            input
        `}
      />
    </div>
  );
}

export default FormInput;
